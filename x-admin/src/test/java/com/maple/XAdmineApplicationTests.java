package com.maple;

import com.maple.sys.entity.Auditor;
import com.maple.sys.mapper.AuditorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class XAdminApplicationTests {

	@Autowired
	private AuditorMapper auditorMapper;

	@Test
	void testMapper() {
		List<Auditor> auditors = auditorMapper.selectAll();
		System.out.println(auditors);
	}

	@Test
	void testGetById(){
		Auditor auditor = auditorMapper.getById(1);
		System.out.println(auditor);
	}

}
