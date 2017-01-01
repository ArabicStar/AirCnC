package presentation.market.accessor;

import java.util.List;

import utils.info.level.LevelStrategy;

public interface LevelAccessor {
	public void setLevelCredit(List<Integer> list);
	
	public LevelStrategy getStrategy();
	
	public void setOldStrategy(LevelStrategy old);
}
