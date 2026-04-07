package org.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.cloud.dto.MemberInfoDTO;

@Mapper
public interface MemberMapper {

	List<MemberInfoDTO> memberList() throws Exception;
	int writeMember(MemberInfoDTO member) throws Exception;
	MemberInfoDTO memberDetail(String id) throws Exception;
	int updateMember(MemberInfoDTO member) throws Exception;
	int deleteMember(String id) throws Exception;
}
