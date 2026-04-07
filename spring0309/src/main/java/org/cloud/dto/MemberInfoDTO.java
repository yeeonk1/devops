package org.cloud.dto;

import lombok.Data;

@Data
public class MemberInfoDTO {
	
	private String id, password, name, gender, birth, mail, phone, address, registDay;
}
