package com.example.board.controller;

import com.example.board.beans.vo.BoardVO;
import com.example.board.services.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

//  프레젠테이션 계층의 구현

    //  Task       URL              Method    Parameter     Form   URL 이동
    //  전체 목록   /Board/list       GET       없음           없음   없음
    //  등록 처리   /Board/register   POST      모든 항목       필요   이동
    //  조회 처리   /Board/read       GET       bno           필요    없음
    //  삭제 처리   /Board/remove     POST      bno           필요    이동
    //  수정 처리   /Board/modify     POST      모든 항목       필요   이동

@Controller
@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("list")
    public String list(Model model){
        log.info("----------------------------------------");
        log.info("list");
        log.info("----------------------------------------");
        model.addAttribute("list", boardService.getList());

        return "board/list";
    }

    @PostMapping("register")
    public RedirectView register(BoardVO boardVO, RedirectAttributes rttr){
        log.info("----------------------------------------");
        log.info("list");
        log.info("----------------------------------------");

        boardService.register(boardVO);

        //  쿼리 스트링으로 전달
        //rttr.addAllAttributes("bno", boardVO.getBno());
        //  세션의 flach 영역으로 전달
        rttr.addFlashAttribute("bno", boardVO.getBno());

        //  RedirectView를 사용하면 redirect 방식으로 전송이 가능하다.
        return new RedirectView("list");
    }
    //  여러 요청을 하나의 메소드로 받을 때에는 {}를 사용하여 콤마로 구분한다.
    @GetMapping({"read", "modify"})
    public void read(@RequestParam("bno") Long bno, Model model, HttpServletRequest request) {
        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);
        //  read 요청 시 read 출력
        //  modify 요청 시 modify 출력
        log.info("----------------------------------------");
        log.info(reqType + " : " + bno);
        log.info("----------------------------------------");

        if (boardService.modify()) {
            log.info("modify : " + bno);
        } else {
            model.addAttribute("board", boardService.get(bno));
            log.info("read : " + bno);
        }
    }

    @GetMapping("modify")
    public RedirectView edit(BoardVO boardVO, RedirectAttributes rttr){
        log.info("----------------------------------------");
        log.info("modify : " + boardVO.toString());
        log.info("----------------------------------------");

        if(boardService.modify(boardVO)){
            rttr.addAttribute("result", "success");
            rttr.addAttribute("bno", boardVO.getBno());
        }
        return new RedirectView("read");
    }
    //  remove로 요청할 수 있는 비지니스 로직 작성
    //  단위 테스트로 전달할 파라미터를 조회
    @GetMapping("remove")
    public RedirectView remove(@RequestParam("bno") Long bno, RedirectAttributes rttr){
        log.info("----------------------------------------");
        log.info("remove : " + bno);
        log.info("----------------------------------------");

        //  삭제 성공 시 result에 "success"를 flash에 담아서 전달
        if(boardService.remove(bno)){
            rttr.addFlashAttribute("result", "success");
        //  삭제 실패 시 result에 "fail"을 flash에 담아서 전달
        }else{
            rttr.addFlashAttribute("result", "fail");
        }
        return new RedirectView("list");
    }

    @GetMapping
    public void goPage(){}
}
