package presentation.market.manager;

import java.util.List;

import utils.info.level.LevelStrategy;

public interface LevelManager {
	public void setLevelStrategy(LevelStrategy ls);
	
	public List<Integer> getLevelCredit();
}
