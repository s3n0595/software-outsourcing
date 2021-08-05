package com.cykj.service.impl;

import com.cykj.bean.Works;
import com.cykj.mapper.WorksMapper;
import com.cykj.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/3 16:33
 * @desc:
 */
@Service
public class WorksServiceImpl implements WorksService {
	@Autowired
	private WorksMapper worksMapper;
	@Override
	public boolean publishWorks(MultipartFile file, Works works) {
		String realPath = null;
		try {
			realPath = ResourceUtils.getURL("classpath:").getPath() + "Provider/" + works.getProviderId();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		File uploadDir = new File(realPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
		System.out.println(realPath);
        works.setAnnexPath(file.getOriginalFilename());
		works.setAuditStatus(7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		works.setReleaseTime(sdf.format(new Date()));
		try {
			file.transferTo(new File(realPath + '/' + works.getAnnexPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(worksMapper.addWorks(works) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Works> queryAllWorks() {
		return worksMapper.queryAllWorks();
	}

	@Override
	public int updatePwd(int providerId, String providerPassword, String password) {
		return worksMapper.updatePwd(providerId,providerPassword,password);
	}
}
