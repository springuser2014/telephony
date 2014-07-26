package telephony.core.service.impl;

import com.google.inject.persist.Transactional;

import telephony.core.entity.jpa.File;
import telephony.core.service.FileService;

/**
 * asd.
 */
public class FileServiceImpl extends AbstractBasicService<File> implements FileService{

	@Transactional
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
