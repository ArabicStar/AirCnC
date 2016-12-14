package interactor.member;

/**
 * A mediate layer for interactive between UI and logic layer about member
 * account.<br>
 * Logically, this interface serves as an informant, that means methods' duties
 * are to transfer a message that a request needs to be handled, then collect
 * necessary information from UI layer or other module (plus completeness
 * check), finally call and pass on information collected to specific logic
 * service which can really handle the request.<br>
 * Then, after logic service complete its job, some results are to be returned.
 * This interactor will use the return value to inform the UI layer to make some
 * response.<br>
 * 
 * @author ClevelandAlto
 *
 */
public interface MemberAccountInteractor {
	public void register();

	public void login();

	public void logout();

	public void refreshCurrentAccount();
}
