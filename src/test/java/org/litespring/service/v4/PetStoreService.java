package org.litespring.service.v4;

import org.litespring.beans.factory.annotation.Autowired;
import org.litespring.beans.factory.annotation.Component;
import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v2.ItemDao;

@Component
public class PetStoreService {
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private ItemDao itemDao;
	public PetStoreService(AccountDao accountDao, ItemDao itemDao) {
		this.accountDao = accountDao;
		this.itemDao = itemDao;
	}
	public AccountDao getAccountDao() {
		return accountDao;
	}
	public ItemDao getItemDao() {
		return itemDao;
	}
}
