// package org.endless.com.thread.task;
//
// import jakarta.validation.constraints.NotEmpty;
// import jakarta.validation.constraints.Pattern;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import org.endless.erp.share.pattern.Regular;
// import org.springframework.data.annotation.Id;
// import org.springframework.data.annotation.Version;
// import org.springframework.data.mongodb.core.mapping.Document;

/**
 * TODO
 * AsyncTaskManager
 *
 * <p>create 2023/05/24 17:54
 *
 * @author Deng Haozhi
 * @since 0.0.3
 */
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Document("async.task.manager")
// public class AsyncTaskManager {
//
//     /**
//      * 复合编号：行业编号+"_"+任务编号
//      */
//     @Id
//     @NotEmpty
//     @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
//     private String id;
//
//     /**
//      * 任务编号，使用异步任务组件名称
//      */
//     @NotEmpty
//     @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
//     private String asyncTaskId;
//
//     /**
//      * 行业编号
//      */
//     @NotEmpty
//     @Pattern(regexp = Regular.ID, message = Regular.ID_MESSAGE)
//     private String industryId;
//
//     /**
//      * 任务状态
//      */
//     private Status status;
//
//     /**
//      * 版本，乐观锁
//      */
//     @Version
//     private Long version;
//
//     /**
//      * 创建时间
//      */
//     private String createDateTime;
//
//     /**
//      * 创建时间戳
//      */
//     private long createTimeStamp;
//
//     /**
//      * 更新时间
//      */
//     private String updateDateTime;
//
//     /**
//      * 更新时间戳
//      */
//     private long updateTimeStamp;
//
//     public enum Status {
//         create("0", "create", "创建"),
//         ready("1", "ready", "就绪"),
//         running("2", "running", "运行"),
//         waiting("3", "waiting", "等待"),
//         exit("4", "exit", "终止");
//
//         private final String statusId;
//         private final String enInstruction;
//         private final String zhInstruction;
//
//         Status(String statusId, String enInstruction, String zhInstruction) {
//             this.statusId = statusId;
//             this.enInstruction = enInstruction;
//             this.zhInstruction = zhInstruction;
//         }
//
//         public String getStatusId() {
//             return statusId;
//         }
//
//         public String getEnInstruction() {
//             return enInstruction;
//         }
//
//         public String getZhInstruction() {
//             return zhInstruction;
//         }
//     }
// }
