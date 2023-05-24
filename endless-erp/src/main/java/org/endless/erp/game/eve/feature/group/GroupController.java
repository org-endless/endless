package org.endless.erp.game.eve.feature.group;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GroupController
 *
 * @author Deng Haozhi
 * @date 2023/3/16 8:19
 * @since 0.0.2
 */
@RestController
@RequestMapping("/erp/eve")
public class GroupController {

    // final GroupService groupService;
    //
    // public GroupController(GroupService groupService) {
    //     this.groupService = groupService;
    // }
    //
    // @GetMapping("/group")
    // @PostMapping("/group")
    // public String group() {
    //
    //     groupService.load("data/groupIDs.yaml");
    //     return "success";
    // }
}