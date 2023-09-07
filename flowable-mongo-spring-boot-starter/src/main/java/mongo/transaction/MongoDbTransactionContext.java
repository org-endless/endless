/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mongo.transaction;

import lombok.extern.slf4j.Slf4j;
import mongo.persistence.MongoDbSession;
import org.flowable.common.engine.impl.cfg.TransactionContext;
import org.flowable.common.engine.impl.cfg.TransactionListener;
import org.flowable.common.engine.impl.cfg.TransactionPropagation;
import org.flowable.common.engine.impl.cfg.TransactionState;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandConfig;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.common.engine.impl.interceptor.CommandExecutor;
import org.flowable.engine.impl.util.CommandContextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joram Barrez
 */
@Slf4j
public class MongoDbTransactionContext implements TransactionContext {

    protected CommandContext commandContext;
    protected MongoDbSession mongoDbSession;
    protected Map<TransactionState, List<TransactionListener>> stateTransactionListeners;

    public MongoDbTransactionContext(CommandContext commandContext) {
        this.commandContext = commandContext;
        this.mongoDbSession = commandContext.getSession(MongoDbSession.class);
    }

    public void commit() {
        log.debug("firing event committing...");
        fireTransactionEvent(TransactionState.COMMITTING, false);

        log.debug("committing transaction...");
        mongoDbSession.getClientSession().commitTransaction();
        log.debug("firing event committed...");
        fireTransactionEvent(TransactionState.COMMITTED, true);
    }

    public void rollback() {
        mongoDbSession.getClientSession().abortTransaction();
    }

    public void addTransactionListener(
            TransactionState transactionState, TransactionListener transactionListener) {
        if (stateTransactionListeners == null) {
            stateTransactionListeners = new HashMap<>();
        }
        List<TransactionListener> transactionListeners =
                stateTransactionListeners.get(transactionState);
        if (transactionListeners == null) {
            transactionListeners = new ArrayList<>();
            stateTransactionListeners.put(transactionState, transactionListeners);
        }
        transactionListeners.add(transactionListener);
    }

    protected void fireTransactionEvent(
            TransactionState transactionState, boolean executeInNewContext) {
        if (stateTransactionListeners == null) {
            return;
        }
        final List<TransactionListener> transactionListeners =
                stateTransactionListeners.get(transactionState);
        if (transactionListeners == null) {
            return;
        }

        if (executeInNewContext) {
            CommandExecutor commandExecutor =
                    CommandContextUtil.getProcessEngineConfiguration().getCommandExecutor();
            CommandConfig commandConfig = new CommandConfig(false, TransactionPropagation.REQUIRES_NEW);
            commandExecutor.execute(
                    commandConfig,
                    new Command<Void>() {
                        @Override
                        public Void execute(CommandContext commandContext) {
                            executeTransactionListeners(transactionListeners, commandContext);
                            return null;
                        }
                    });
        } else {
            executeTransactionListeners(transactionListeners, commandContext);
        }
    }

    protected void executeTransactionListeners(
            List<TransactionListener> transactionListeners, CommandContext commandContext) {
        for (TransactionListener transactionListener : transactionListeners) {
            transactionListener.execute(commandContext);
        }
    }
}
