package hello;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    private final String SSUser = "ss";
    
    private static HashMap<String,UserData> userMap;
    private static Random random = new Random();
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	String userName = String.format(template, name);

    	UserData userData = new UserData();
    	
    	int cost   = random.nextInt(10);
    	int hp     = random.nextInt(10)+1;
    	int attack = random.nextInt(10)+1;

    	if(userMap.get(userName)!=null){
    		userData = userMap.get(userName);
    	}else{
    		userData.setId(counter.incrementAndGet());
    		userData.setCost(cost);
    		userData.setHp(hp);
    		userData.setAttack(attack);
    	}
    	userMap.put(userName, userData);
        return new Greeting(userData.getId(),userName,userData.getCost(),userData.getHp(),userData.getAttack());
    }
    
    @RequestMapping("/text1")
    public String text1(@RequestParam(value="name", defaultValue="World") String name) {
    	String userName = String.format(template, name);

    	UserData userData = new UserData();
    	
    	int cost   = random.nextInt(10);
    	int hp     = random.nextInt(10)+1;
    	int attack = random.nextInt(10)+1;

    	if(userMap.get(userName)!=null){
    		userData = userMap.get(userName);
    	}else{
    		userData.setId(counter.incrementAndGet());
    		userData.setCost(cost);
    		userData.setHp(hp);
    		userData.setAttack(attack);
    	}
    	userMap.put(userName, userData);

        return makeText(userData.getId(),userName,userData.getCost(),userData.getHp(),userData.getAttack());
    }

    public static String makeText(long id, String content , int cost , int hp , int attack){
    	StringBuilder sb = new StringBuilder();
    	sb.append("id : ").append(id).append("<br>");
    	sb.append("name : ").append(content).append("<br>");
    	sb.append("cost : ").append(cost).append("<br>");
    	sb.append("hp : ").append(hp).append("<br>");
    	sb.append("attack : ").append(attack).append("<br>");
    	
    	if(userMap.size()>1){
    		for(Entry<String,UserData> entry : userMap.entrySet()){
    			if(entry.getKey().equals(content)){
    				continue;
    			}
    			int r = random.nextInt(userMap.size());
    			if(r > userMap.size()/2){
    				continue;
    			}
    			UserData enemyData = entry.getValue();
    			int enemyHp = enemyData.getHp();
    			int enemyAttack = enemyData.getAttack();
    			
    	    	sb.append("<br>");
    	    	sb.append("EnemyClass!!").append("<br>");
    	    	sb.append("id : ").append(enemyData.getId()).append("<br>");
    	    	sb.append("name : ").append(entry.getKey()).append("<br>");
    	    	sb.append("cost : ").append(enemyData.getCost()).append("<br>");
    	    	sb.append("hp : ").append(enemyHp).append("<br>");
    	    	sb.append("attack : ").append(enemyAttack).append("<br>");
    	    	
    	    	sb.append("<br>");
    	    	sb.append("result").append("<br>");

    	    	sb.append("name : ").append(content).append("<br>");
    	    	sb.append("hp : ").append(hp - enemyAttack).append("<br>");
    	    	if(hp - enemyAttack <= 0){
        	    	sb.append("player dead!!!!!").append("<br>");
    	    	}
    	    	
    	    	sb.append("name : ").append(entry.getKey()).append("<br>");
    	    	sb.append("hp : ").append(enemyHp - attack).append("<br>");
    	    	if(enemyHp - attack <= 0){
        	    	sb.append("enemy dead!!!!!").append("<br>");
    	    	}
    	    	break;
    		}
    	}
    	
    	
    	
    	return sb.toString();
    }
    
    public static void initialize(){
    	userMap = new HashMap<String,UserData>();
    	System.out.println("initialize!!");
    }
}
