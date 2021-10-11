package com.example.board.services;

import com.example.board.beans.vo.BoardVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    public void register(BoardVO board);
    public BoardVO get(Long bno);
    public boolean modify(BoardVO board);
    public boolean remove(Long bno);
    public List<BoardVO> getList();
}
