package org.cloud.service;

import java.util.List;

import org.cloud.dto.MemberInfoDTO;

public interface MemberService {

	List<MemberInfoDTO> memberList() throws Exception;
	int writeMember(MemberInfoDTO member) throws Exception;
	MemberInfoDTO memberDetail(String id) throws Exception;
	int updateMember(MemberInfoDTO member) throws Exception;
	int deleteMember(String id) throws Exception;
}
