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
import java.util.*;

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
			realPath = ResourceUtils.getURL("classpath:").getPath() + "Provider";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		File uploadDir = new File(realPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
		System.out.println(realPath);
        works.setAnnexPath(file.getOriginalFilename());
		works.setAuditStatus(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		works.setReleaseTime(sdf.format(new Date()));
		try {
			file.transferTo(new File(realPath + '/' + works.getAnnexPath()));
			if(worksMapper.addWorks(works) > 0) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int insertWorks(Works works) {
		works.setAuditStatus(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		works.setReleaseTime(sdf.format(new Date()));
		if(worksMapper.insertWorks(works) > 0) {
			return worksMapper.selectLastWork();
		} else {
			return -1;
		}
	}

	@Override
	public boolean updateWorks(Works works) {
		works.setWorksId(worksMapper.selectLastWork());
		if(worksMapper.updateImgUrl(works) > 0) {
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
	public List<Works> queryMyWorks(int providerId) {
		return worksMapper.queryMyWorks(providerId);
	}
	@Override
	public int updatePwd(int providerId, String providerPassword, String password) {
		return worksMapper.updatePwd(providerId,providerPassword,password);
	}


	//小程序添加作品
	@Override
	public int wechatAddWorks(Works works) {
		int i = worksMapper.wechatAddWorks(works);
		return i;
	}

	@Override
	public Map<String, String> uploadFile(MultipartFile[] file) {
		String realPath = null;
		try {
			realPath = ResourceUtils.getURL("classpath:").getPath() + "Provider";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		File uploadDir = new File(realPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String allFileName = "";
        for(int i = 0; i < file.length; i++) {
	        String uuid = UUID.randomUUID().toString();
	        String fileName = uuid + file[i].getOriginalFilename();
	        try {
		        file[i].transferTo(new File(realPath, fileName));
				if(i != 0) allFileName += "::";
				allFileName += fileName;
	        } catch (IOException e) {
		        e.printStackTrace();
	        }
        }
		Map<String,String> result = new HashMap<String, String>();
        result.put("annexPath", allFileName.split("::")[0]);
        result.put("imgUrl", allFileName);
		return result;
	}
}
