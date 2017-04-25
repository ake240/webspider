package com.datayes.webspider.service.website;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.machine.IMachineDao;
import com.datayes.webspider.dao.website.IWebSiteVerifyCodeDao;
import com.datayes.webspider.domain.machine.Machine;
import com.datayes.webspider.domain.website.WebSiteVerifyCode;

@Service("webSiteVerifyCodeService")
public class WebSiteVerifyCodeServiceImpl implements IWebSiteVerifyCodeService {

	@Resource
	private IWebSiteVerifyCodeDao webSiteVerifyCodeDao;
	
	@Resource
	private IMachineDao machineDao;
	
	@Override
	public PageDTO enquiryWebSiteVerifyCodePage(Integer webSiteId, int pageNow, int pageSize) {
		int total = webSiteVerifyCodeDao.enquiryWebSiteVerifyCodeCount(webSiteId);
		List list = webSiteVerifyCodeDao.enquiryWebSiteVerifyCodePage(webSiteId, pageNow, pageSize);
		convertHost(list);
		return new PageDTO(list, pageNow, pageSize, total);
	}
	
	private void convertHost(List<WebSiteVerifyCode> list) {
		for (WebSiteVerifyCode webSiteVerifyCode : list) {
			String host = webSiteVerifyCode.getMachineHost();
			String url = webSiteVerifyCode.getVerifyCodeUrl();
			Machine machine = machineDao.findByHost(host);
			if (machine != null) {
				String destHost = machine.getMachineDesc();
				url = "http://" + destHost + url.substring(url.indexOf(":8081"));
				webSiteVerifyCode.setVerifyCodeUrl(url);
			}
		}
	}

	@Override
	@Transactional
	public void saveOrUpdate(WebSiteVerifyCode verifyCode) {
		webSiteVerifyCodeDao.saveOrUpdate(verifyCode);
	}

	@Override
	public WebSiteVerifyCode findByVerifyCodeId(Integer verifyCodeId) {
		return webSiteVerifyCodeDao.findById("findByVerifyCodeId", verifyCodeId);
	}

}
