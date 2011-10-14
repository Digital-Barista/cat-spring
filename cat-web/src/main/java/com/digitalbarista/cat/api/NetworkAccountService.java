package com.digitalbarista.cat.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.digitalbarista.cat.dao.NetworkAccountDao;
import com.digitalbarista.cat.data.Client;
import com.digitalbarista.cat.data.NetworkAccount;
import com.digitalbarista.cat.util.SerializableList;

@Controller
@Transactional
public class NetworkAccountService {
	
	@Autowired
	private NetworkAccountDao netAccountDao;
	
	@RequestMapping(value="/network-accounts",method={RequestMethod.GET})
	public SerializableList<NetworkAccount> getAllNetworkAccounts()
	{
		return new SerializableList<NetworkAccount>(netAccountDao.getAllNetworkAccounts());
	}
	
	@RequestMapping(value="/network-accounts/{id}",method={RequestMethod.GET})
	public NetworkAccount getNetworkAccount(@PathVariable long id)
	{
		return netAccountDao.getNetworkAccount(id);
	}
	
	@RequestMapping(value="/network-accounts",method={RequestMethod.POST})
	public NetworkAccount saveClient(@RequestBody @Valid NetworkAccount netAccount)
	{
		return netAccountDao.save(netAccount);
	}
}
