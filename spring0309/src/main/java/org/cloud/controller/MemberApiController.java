package org.cloud.controller;

import java.util.List;

import org.cloud.dto.MemberInfoDTO;
import org.cloud.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberApiController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/list")
	public List<MemberInfoDTO> openMemberList(Model model) throws Exception { // Select
		List<MemberInfoDTO> member = memberService.memberList();
		model.addAttribute("list", member);
		return member;
	}
	
	/*
	 * @GetMapping("/writeui") public String writeUI() { // 등록 UI return "writeUi";
	 * }
	 */
	
	@PostMapping("/write")
	public String memberWrite(@RequestBody MemberInfoDTO member) throws Exception { // insert
		memberService.writeMember(member);
		return "write success";
	}
	
	@GetMapping("/detail/{id}")
	public MemberInfoDTO memberDetail(@PathVariable("id") String id, Model model) throws Exception {
		MemberInfoDTO member = memberService.memberDetail(id);
		model.addAttribute("member", member);
		return member;
	}
	
	@PutMapping("/update/{id}")
	public MemberInfoDTO memberUpdate(@PathVariable("id") String id, @RequestBody MemberInfoDTO member) throws Exception {
		member.setId(id);
		memberService.updateMember(member);
		return member;
	}
	
	@DeleteMapping("/delete/{id}")
	public String memberDelete(@PathVariable("id") String id) throws Exception {
		memberService.deleteMember(id);
		return "delete success";
	}
}
