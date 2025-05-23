package com.test.admin.board;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.test.admin.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardServiceImpl<T extends Board, D extends BoardDTO> implements BoardService<T, D> {

	private final BoardRepository<T> repository;

	@Override
	public Page<D> getList(int page, int size) {
		
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "seq"));
		Page<T> entityPage = repository.findAll(pageable);
        return entityPage.map(entity -> (D) entity.toDTO()); 
	}

	@Override
	public Optional<D> get(Long seq) {
        return repository.findById(seq).map(entity -> (D) entity.toDTO());
	}

	@Override
	public D create(D dto) {
		return save(dto);
	}

	@Override
	public D update(D dto) {
		return save(dto);
	}

	@Override
	public void delete(Long seq) {
		repository.deleteById(seq);
	}

	private D save(D dto) {
		return (D) repository.save((T) dto.toEntity()).toDTO();
	}

	@Override
	public long count() {
		return repository.count();
	}

	public PageDTO getPagenation(int page, int size) {
		
		int blockSize = 10;
		PageDTO dto = new PageDTO(page, size, count(), blockSize);
		return dto;
	}
}
