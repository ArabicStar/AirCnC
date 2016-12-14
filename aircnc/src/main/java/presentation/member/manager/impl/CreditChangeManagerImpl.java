package presentation.member.manager.impl;

import static utils.exception.StaticExceptionFactory.duplicateSingletonEx;
import static utils.exception.StaticExceptionFactory.singletonNotExistsEx;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.member.manager.CreditChangeManager;
import presentation.member.model.CreditModel;
import vo.member.credit.CreditChangeVo;

/**
 * the manager of member info
 * aiming to receive the MemberVo from the logic layer
 * and deliver the member info model to the presentation layer
 * @author paranoia
 *
 */
public class CreditChangeManagerImpl implements CreditChangeManager{
	
	private static CreditChangeManager instance;
	
	private List<CreditChangeVo> changes;
	
	public static final CreditChangeManager launch() {
		if (instance != null)
			throw duplicateSingletonEx();

		return instance = new CreditChangeManagerImpl();
	}
	
	public static final CreditChangeManager getInstance(){
		if (instance == null)
			throw singletonNotExistsEx();

		return instance;
	}
	
	private ObservableList<CreditModel> creditChangesData;
	@Override
	public boolean setCreditChanges(List<CreditChangeVo> list) {
		if(list!=null){
			this.changes = list;
			return true;
		}
		return false;
	}
	
	/**
	 * wrap into the observablelist
	 */
	@Override
	public ObservableList<CreditModel> getCreditList() {
		creditChangesData = FXCollections.observableArrayList();
		Iterator<CreditChangeVo> it = changes.iterator();
		while(it.hasNext())
			creditChangesData.add(new CreditModel(it.next()));
		return creditChangesData;
	}

}
