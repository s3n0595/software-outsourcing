package com.cykj.service;

import com.cykj.bean.Works;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/3 16:32
 * @desc:
 */
public interface WorksService {
	boolean publishWorks(MultipartFile file, Works works);
	List<Works> queryAllWorks();
}
