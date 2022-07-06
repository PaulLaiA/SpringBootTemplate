package org.dpaul.template.springboot.control;

import org.dpaul.template.springboot.common.response.Result;
import org.dpaul.template.springboot.common.response.annotation.ResponseResultBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ResponseResultBody
@RestController
public class indexController {

	@RequestMapping("/index")
	public Result<Void> index(){
		return Result.success();
	}
}