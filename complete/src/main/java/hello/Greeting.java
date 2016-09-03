package hello;

public class Greeting {

    private final long id;
    private final String content;

    private final int cost;
    private final int hp;
    private final int attack;
    
    public Greeting(long id, String content , int cost , int hp , int attack) {
        this.id = id;
        this.content = content;
        this.cost = cost;
        this.hp = hp;
        this.attack = attack;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

	public int getCost() {
		return cost;
	}

	public int getHp() {
		return hp;
	}

	public int getAttack() {
		return attack;
	}
    
}
