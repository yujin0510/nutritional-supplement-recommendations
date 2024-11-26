package com.test.nutri.service;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.nutri.entity.Member;
import com.test.nutri.model.MemberDTO;
import com.test.nutri.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder
	bCryptPasswordEncoder;
	
	public void signup(MemberDTO dto) {
		
		boolean result = memberRepository.existsByUsername(dto.getUsername());
		
		if (result) {
			System.out.println("이미 사용중인 아이디입니다.");
			return;
		}
		
		Member member = Member.builder()
							.username(dto.getUsername())
							.email(dto.getEmail())
							.password(bCryptPasswordEncoder.encode(dto.getPassword()))
							.name(dto.getName())
							.nickname(dto.getNickname())
							.dob(dto.getDob())
							.gender(dto.getGender())
							.telephone(dto.getTelephone())
							.address(dto.getAddress())
							.build();
		memberRepository.save(member);
	}

	public boolean isUsernameAvailable(String username) {
		
		return !memberRepository.existsByUsername(username); 
	}
	
}
