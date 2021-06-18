package com.encoding.service;

import com.encoding.dto.FileDTO;

public interface IEncodeService {

	FileDTO encode(FileDTO fileDto);
	FileDTO decode(FileDTO fileDto);
}
