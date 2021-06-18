package com.encoding.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.encoding.dto.FileDTO;
import com.encoding.service.IEncodeService;
import com.encoding.utils.Utils;

@CrossOrigin
@RestController
public class EncodeAPI {

	@Autowired
	private IEncodeService encodeService;

	@PostMapping("/api/encode")
	public String encode(@RequestBody FileDTO fileDto) {
		fileDto = encodeService.encode(fileDto);
		return "/api/getFile/" + fileDto.getFileName() + "." + fileDto.getFileType();
		
	}

	@PostMapping("/api/decode")
	public String decode(@RequestBody FileDTO fileDto) {
		fileDto = encodeService.decode(fileDto);
		return "/api/getFile/" + fileDto.getFileName() + "." + fileDto.getFileType();
	}

	@GetMapping("/api/getFile/{fileName}.{fileType}")
	public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName, @PathVariable("fileType") String fileType) {

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + "." + fileType)
				.body(Utils.getByteBySource("src/main/resources/" + fileName + "." + fileType));
	}

}
