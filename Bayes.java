import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
public class Bayes {

  public static void main(String[] args) throws IOException {
	
	DecimalFormat df=new DecimalFormat("#.####");
	df.setRoundingMode(RoundingMode.CEILING);
	List<Integer> train=new ArrayList<Integer>();
	List<Integer> baseball = new ArrayList<Integer>();
	List<Integer> watch = new ArrayList<Integer>();
	List<Integer> outfood = new ArrayList<Integer>();
	List<Integer> feed = new ArrayList<Integer>();
    Scanner sc = new Scanner(new File("Bayes.txt"));
    sc.useDelimiter("");
    while (sc.hasNext()) {
	String s = sc.next();
      if (s.trim().isEmpty()) {
        continue;
      }
	else
	{
		int x =Integer.parseInt(s);
		train.add(x);
		
	}
      //System.out.println(s);
    }
    sc.close();
	int count=0;
	for(int j=0; j<train.size();j++)
	{
		baseball.add(train.get(j));
		watch.add(train.get(j+1));
		outfood.add(train.get(j+2));
		feed.add(train.get(j+3));
		j=j+3;
		count=count+1;
	}
	//System.out.println(count);
	//System.out.println(train.size());
	
	//calculating probability of baseball in TV
	int sum=0;
	int summa=0;
	for(int i=0; i<baseball.size();i++)
	{
		if(baseball.get(i)==0)
		{
			sum=sum+1;
		}
		else
		{
			summa=summa+1;
		}
	}
	
	System.out.println("Probablity of Baseball game on TV:");
	List<Float> base = new ArrayList<Float>();
	float base_false=(float) sum/baseball.size();
	float bf=Float.parseFloat(df.format(base_false));
	base.add(bf);
	//System.out.println(base.get(0));
	
	float base_true=(float) summa/baseball.size();
	float bt=Float.parseFloat(df.format(base_true));
	base.add(bt);
	System.out.println(base.get(1));
	
	
	//calculating problability of georage watching Tv while baseball game is on
	int watch_sum=0;
	int watch_summa=0;
	for(int i=0; i<watch.size();i++)
	{
		if((watch.get(i)==1) && (baseball.get(i)==0))
		{
			watch_sum=watch_sum+1;
		}
		else if((watch.get(i)==1) && (baseball.get(i)==1))
		{
			watch_summa=watch_summa+1;
		}
		else
		{
			continue;
		}
	}
	//System.out.println(watch_sum);
	//System.out.println(watch_summa);
	
	System.out.print("Probability of George watching game= False:");
	//Probability of watching Tv given that baseball game is not on
	List<Float> watching = new ArrayList<Float>();
	float watch_false=(float) watch_sum/watch.size();
	float watch_false_base=(float) watch_false/base.get(0);
	float watch_bf=Float.parseFloat(df.format(watch_false_base));
	watching.add(watch_bf);
	System.out.println(watching.get(0));
	
	System.out.print("Probability of George watching baseball game= True:");
	//probability of watching TV given that baseball game is on.
	float watch_true=(float) watch_summa/watch.size();
	float watch_true_base=(float) watch_true/base.get(1);
	float watch_bt=Float.parseFloat(df.format(watch_true_base));
	watching.add(watch_bt);
	System.out.println(watching.get(1));
	
	//calculating problability of  out of cat food
	int out_sum=0;
	int out_summa=0;
	for(int i=0; i<outfood.size();i++)
	{
		if(outfood.get(i)==0)
		{
			out_sum=out_sum+1;
		}
		else
		{
			out_summa=out_summa+1;
		}
	}
	//System.out.println(out_sum);
	//System.out.println(out_summa);
	System.out.print("Probability of Food out:");
	List<Float> outing = new ArrayList<Float>();
	float out_false=(float) out_sum/outfood.size();
	float out_bf=Float.parseFloat(df.format(out_false));
	outing.add(out_bf);
	//System.out.println(outing.get(0));
		
	float out_true=(float) out_summa/outfood.size();
	float out_bt=Float.parseFloat(df.format(out_true));
	outing.add(out_bt);
	System.out.println(outing.get(1));
	
	//calculating problability of  feed food
	int feed_watch_false=0;
	int feed_watch_false_true=0;
	int feed_watch_true_false=0;
	int feed_watch_true_true=0;
	for(int i=0; i<feed.size();i++)
	{
			//donot watch and food is out
		if ((feed.get(i)==1) && (watch.get(i)==0) && (outfood.get(i)==0))
		{
			feed_watch_false=feed_watch_false+1;
		}
			//donot watch and food is not out
		else if((feed.get(i)==1) && (watch.get(i)==0) && (outfood.get(i)==1))
		{
			feed_watch_false_true=feed_watch_false_true+1;
		}
			//watch and food is out
		else if((feed.get(i)==1) && (watch.get(i)==1) && (outfood.get(i)==0))
		{
			feed_watch_true_false=feed_watch_true_false+1;
		}
			//watch and food not out
		else if((feed.get(i)==1) && (watch.get(i)==1) && (outfood.get(i)==1))
		{
			feed_watch_true_true=feed_watch_true_true+1;
		}
		
		else
		{
			continue;
		}
	}
	
	//System.out.println(feed_watch_false);
	//System.out.println(feed_watch_false_true);
	//System.out.println(feed_watch_true_false);
	//System.out.println(feed_watch_true_true);
	
	int watch_out_false=0;
	int watch_false_true=0;
	int watch_true_false=0;
	int watch_true_true=0;
	for(int i=0; i<watch.size();i++)
	{
		if((watch.get(i)==0) && (outfood.get(i)==0))
		{
			watch_out_false=watch_out_false+1;
		}
		else if((watch.get(i)==0) && (outfood.get(i)==1))
		{
			watch_false_true=watch_false_true+1;
		}
		
		else if((watch.get(i)==1) && (outfood.get(i)==0))
		{
			watch_true_false=watch_true_false+1;
		}
		
		else if((watch.get(i)==1) && (outfood.get(i)==1))
		{
			watch_true_true=watch_true_true+1;
		}
		else
		{
			continue;
		}
	}
	//System.out.println(watch_out_false);
	//System.out.println(watch_false_true);
	//System.out.println(watch_true_false);
	//System.out.println(watch_true_true);
	List<Float> feeding = new ArrayList<Float>();
	
	
	//when watching is false and out is false
	System.out.println("George feed cat given george watch tv and out of food");
	float feed_false_watch=(float) feed_watch_false/feed.size();
	float feed_false_watch_out=(float) watch_out_false/feed.size();
	float feed_false_feed=(float) feed_false_watch/feed_false_watch_out;
	float feed_bf=Float.parseFloat(df.format(feed_false_feed));
	feeding.add(feed_bf);
	System.out.print("George watch=false and food out=false:");
	System.out.println(feeding.get(0));
	
	//when watching is false and out is true
	float feed_false_out=(float) feed_watch_false_true/feed.size();
	float feed_false_watch_out_2=(float) watch_false_true/feed.size();
	float feed_false_two=(float) feed_false_out/feed_false_watch_out_2;
	float feed_gf=Float.parseFloat(df.format(feed_false_two));
	feeding.add(feed_gf);
	System.out.print("George watch=false and food out=true:");
	System.out.println(feeding.get(1));
	
	//when watching is true and feeding of food is true
	float feed_true_watch=(float) feed_watch_true_false/feed.size();
	float feed_false_watch_out_3=(float) watch_true_false/feed.size();
	float feed_true_feed=(float) feed_true_watch/feed_false_watch_out_3;
	float feed_bt=Float.parseFloat(df.format(feed_true_feed));
	feeding.add(feed_bt);
	System.out.print("George watch=True and food out=false:");
	System.out.println(feeding.get(2));
	
	//when outing is true and feeding of food is true
	float feed_true_out=(float) feed_watch_true_true/feed.size();
	float feed_false_watch_out_4=(float) watch_true_true/feed.size();
	float feed_true_two=(float) feed_true_out/feed_false_watch_out_4;
	float feed_gt=Float.parseFloat(df.format(feed_true_two));
	feeding.add(feed_gt);
	System.out.print("George watch=True and food out=True:");
	System.out.println(feeding.get(3));

  }

}