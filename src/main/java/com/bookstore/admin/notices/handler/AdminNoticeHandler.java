package com.bookstore.admin.notices.handler;

import com.bookstore.admin.notices.service.IAdminNoticeService;
import com.bookstore.commons.beans.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeHandler {
    @Autowired
    private IAdminNoticeService iAdminNoticeService;

    //查询所有公告
    @RequestMapping("/findNotices")
    public String findNotices(Model model){
        List<Notice> notices = iAdminNoticeService.findAllNotices();
        model.addAttribute("notices",notices);
        return "/admin/notices/list.jsp";
    }

    //根据id查询公告详细信息
    @RequestMapping("/findByIdNotice")
    public String findByIdNotice(Integer id,Model model){
        Notice notice = iAdminNoticeService.findByIdNotice(id);
        model.addAttribute("n",notice);
        return "/admin/notices/edit.jsp";
    }

    //编辑公告
    @RequestMapping("/editNotice")
    public String editNotice(Notice notice){
        int row = iAdminNoticeService.editNotice(notice);
        return "/admin/notice/findNotices";
    }

    //添加公告
    @RequestMapping ("/addNotice")
    public String addNotice(Notice notice){
        System.out.println("notice的值是：---" + notice + ",当前方法=AdminNoticeHandler.addNotice()");
        int row = iAdminNoticeService.addNotice(notice);
        return "/admin/notice/findNotices";
    }

    //删除公告
    @RequestMapping("/deleteNotice")
    public String removeNotice(Integer id){
        int row = iAdminNoticeService.removeNotice(id);
        return "/admin/notice/findNotices";
    }
}
