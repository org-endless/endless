package org.endless.erp.share.thread.task;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.endless.erp.share.pattern.Regular;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * AsyncTaskManager
 * @author Deng Haozhi
 * @date 2023/5/24 11:49
 * @since 0.0.3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("async.task.Manager")
public class AsyncTaskManager {

    /**
     * 任务编号，使用异步任务组件名称
     */
    private String asyncTaskId;

    /**
     * 任务状态
     */
    private Status status;

    /**
     * 创建时间
     */
    @Pattern(regexp = Regular.DATE, message = Regular.DATE_MESSAGE)
    private String createDateTime;

    /**
     * 创建时间戳
     */
    @Pattern(regexp = Regular.TIME, message = Regular.TIME_MESSAGE)
    private long createTimeStamp;

    /**
     * 更新时间
     */
    @Pattern(regexp = Regular.DATE, message = Regular.DATE_MESSAGE)
    private String updateDateTime;

    /**
     * 更新时间戳
     */
    @Pattern(regexp = Regular.TIME, message = Regular.TIME_MESSAGE)
    private long updateTimeStamp;

    public enum Status {

        create("0", "create", "创建"),
        ready("1", "ready", "就绪"),
        running("2", "running", "运行"),
        wait("3", "wait", "等待"),
        exit("4", "exit", "终止");

        private final String statusId;
        private final String enInstruction;
        private final String zhInstruction;

        Status(String statusId, String enInstruction, String zhInstruction) {
            this.statusId = statusId;
            this.enInstruction = enInstruction;
            this.zhInstruction = zhInstruction;
        }

        public String getStatusId() {
            return statusId;
        }

        public String getEnInstruction() {
            return enInstruction;
        }

        public String getZhInstruction() {
            return zhInstruction;
        }
    }
}