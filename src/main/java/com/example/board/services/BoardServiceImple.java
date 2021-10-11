package com.example.board.service;

import com.example.board.beans.vo.BoardVO;
import com.example.board.services.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

    //  비지니스 계층
    //  프레젠테이션 계층(view)와 영속 계층(DBMS)의 중간 역할을 한다.
    //  영속 계층은 DBMS를 기준으로, 비즈니스 계층은 로직을 기준으로 처리한다.
    //  예를 들어 쇼핑몰에서 상품 구매 시 포인트 적립을 한다고 가정한다면,
    //  영속 계층의 설계는 '상품', '회원'으로 나누어 설계하지만, 비지니스 계층은
    //  상품 영역과 히ㅗ원 영역을 동시에 사용해서 하나의 로직을 처리하게 된다.


@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService {

    @Override
    public void register(BoardVO board) {

    }

    @Override
    public BoardVO get(Long bno) {
        return null;
    }

    @Override
    public boolean modify(BoardVO board) {
        return false;
    }

    @Override
    public boolean remove(Long bno) {
        return false;
    }

    @Override
    public List<BoardVO> getList() {
        return null;
    }
}