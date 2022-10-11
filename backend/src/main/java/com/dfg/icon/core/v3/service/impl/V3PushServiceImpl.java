package com.dfg.icon.core.v3.service.impl;

import com.dfg.icon.core.common.service.ScheduleService;
import com.dfg.icon.core.dao.icon.*;
import com.dfg.icon.core.exception.IconCode;
import com.dfg.icon.core.mappers.icon.*;
import com.dfg.icon.core.v2.vo.GenesisAccVo;
import com.dfg.icon.core.v3.adapter.V3BlockChainAdapter;
import com.dfg.icon.core.v3.service.V3PushService;
import com.dfg.icon.core.v3.vo.DecimalType;
import com.dfg.icon.core.v3.vo.GenesisRpcRes;
import com.dfg.icon.util.DateUtil;
import com.dfg.icon.util.HexUtil;
import com.dfg.icon.web.v3.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bsm
 * 2018-01-22
 */
@Service
public class V3PushServiceImpl implements V3PushService {


	@Autowired
	TAddressPushMapper tAddressPushMapper;

	@Override
	public CommonRes isRegistered(PushKeyReq req) throws Exception {

		CommonRes res = new CommonRes();

		TAddressPushExample example = new TAddressPushExample();
		example.createCriteria().andAddressEqualTo(req.getAddress()).andEndpointEqualTo(req.getEndpoint());

		List<TAddressPush> push = tAddressPushMapper.selectByExample(example);

		if(push != null && push.size() > 0) {
			res.setCode(IconCode.SUCCESS);
		} else {
			res.setCode(IconCode.NO_DATA);
		}

		return res;
	}

	@Override
	public CommonRes registerPush(PushReq req) throws Exception {

		CommonRes res = new CommonRes();

		TAddressPushExample example = new TAddressPushExample();
		example.createCriteria().andAddressEqualTo(req.getAddress()).andEndpointEqualTo(req.getEndpoint());

		List<TAddressPush> list = tAddressPushMapper.selectByExample(example);

		if(list.size()>0) {
			res.setCode(IconCode.DUPLICATE);
		} else {
			TAddressPush push = new TAddressPush();
			push.setAddress(req.getAddress());
			push.setEndpoint(req.getEndpoint());
			push.setP256dh(req.getP256dh());
			push.setAuth(req.getAuth());
			push.setExpireDate(req.getExpireDate());
			push.setUpdateDate(DateUtil.getNowDate());
			tAddressPushMapper.insert(push);

			res.setCode(IconCode.SUCCESS);
		}

		return res;
	}

	@Override
	public CommonRes withdrawPush(PushKeyReq req) throws Exception {

		CommonRes res = new CommonRes();

		TAddressPushExample example = new TAddressPushExample();
		example.createCriteria().andAddressEqualTo(req.getAddress()).andEndpointEqualTo(req.getEndpoint());

		tAddressPushMapper.deleteByExample(example);
		res.setCode(IconCode.SUCCESS);

		return res;
	}
}
