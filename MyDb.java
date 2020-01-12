import java.io.*;
import java.util.Scanner;


//Node class 
class Node
{
	int rno;
	String name;
	int age;
	Node next;
	
	public Node()
	{
		next=null;
	}
}

//Student class whose one object will be a separate linked list

class Student
{
	private Node head;
	private int size;
	
	public Student()
	{
		this.head=null;
		size=0;
	}
	
	
	public void insert(int rno,String name,int age)
	{
		Node newn=new Node();
		newn.rno=rno;
		newn.name=name;
		newn.age=age;
		
		if(this.head==null)
		{
			head=newn;
		}
		else
		{
			Node temp=this.head;
			while(temp.next!=null)
			{
				if((temp.rno==rno)||(temp.next.rno==rno))
				{
					break;
				}
				temp=temp.next;
			}
			if(temp.next!=null)
			{
				System.out.println("rno already exist");
				return;
			}
			else
			{
				temp.next=newn;
				
			}
			
		}
		System.out.println("Record successfully inserted...");	
		size++;
	}
	
	public void display(String option)
	{
		Node temp=this.head;
		System.out.printf("\n%-15s%-50S%-15s\n\n","Roll No.","Student Name","Age");
		while(temp!=null)
		{
			System.out.printf("%-15d%-50S%-15d\n",temp.rno,temp.name,temp.age);
			temp=temp.next;
		}
	}
	
	public void deleteall()
	{
		this.head=null;
		this.size=0;
		System.gc();
	}
	
	public int count()
	{
		return this.size;
	}
	
	public void delete(String colname,String value)
	{
		if(colname.equals("rno")||colname.equals("name"))
		{
			Node temp=this.head;
			
			if(colname.equals("rno"))
			{
				if(Integer.parseInt(value)==head.rno)
				{
					if(this.size==1)
					{
						head=null;
						this.size=0;
					}
					else
					{
						this.head=this.head.next;
						size--;
					}
					System.out.println("Record successfully deleted...");
					return;
				}
				int i=0;
				for(i=1;i<size;i++)
				{
					if(temp.next.rno==Integer.parseInt(value))
					{
						break;
					}
					temp=temp.next;
				}
				if(i<this.size)
				{
					temp.next=temp.next.next;
					size--;
					System.out.println("Record successfully deleted...");
				}
				else
				{
					System.out.println("There is no such a record");
				}
			}
			else if(colname.equals("name"))
			{
				if(value.equals(head.name))
				{
					if(this.size==1)
					{
						head=null;
						this.size=0;
					}
					else
					{
						this.head=this.head.next;
						size--;
					}
					System.out.println("Record successfully deleted...");
					return;
				}
				int i=0;
				for(i=1;i<this.size;i++)
				{
					if(value.equals(temp.next.name))
					{
						break;
					}
					temp=temp.next;
				}
				if(i<this.size)
				{
					temp.next=temp.next.next;
					size--;
					System.out.println("Record successfully deleted...");
				}
				else
				{
					System.out.println("There is no such a record");
				}
			}
		}
		else
		{
			System.out.println("Invalid column name");
			return;
		}
	}
	
	public void update()
	{
		Scanner sobj=new Scanner(System.in);
		System.out.println("1. name \n2. age");
		int colname=sobj.nextInt();
		if((colname!=1)&&(colname!=2))
		{
			System.out.println("invalid field name");
			return;
		}
		
		System.out.println("SET "+(colname==1?"name=":"age="));
		sobj.nextLine();
		String newvalue=sobj.nextLine();
		
		System.out.println("WHERE \n 1. rno\n 2. name");
		int condition=sobj.nextInt();
		
		if((condition!=1)&&(condition!=2))
		{
			System.out.println("invalid field name");
			return;
		}
		
		System.out.println("WHERE "+(condition==1?"rno=":"name="));
		sobj.nextLine();
		String oldvalue=sobj.nextLine();
		
		Node temp=this.head;
		int i=0;
		//System.out.println(oldvalue);
		for(i=0;i<size;i++)
		{
			if((temp.name.equals(oldvalue))||((condition==1)&&(temp.rno==Integer.parseInt(oldvalue))))
			{
				break;
			}
			//System.out.println(temp.name);
			temp=temp.next;
		}
		if(i<this.size)
		{
			if(colname==1)
			{
				temp.name=newvalue;
				System.out.println("Record successfully updated");
			}
			else
			{
				try
				{
					temp.age=Integer.parseInt(newvalue);
					System.out.println("Record successfully updated");
				}
				catch(Exception e)
				{
					System.out.println("Invalid value for integer type field");
				}
			}
		}
		else
		{
			System.out.println("There is no such a record");
		}
	}	
}

//Entry point function
class MyDb
{
	public static void main(String arg[]) throws Exception	
	{
		Student obj=null;
		//obj.display();
		
		Scanner sobj=new Scanner(System.in);
		
		while(true)
		{
			System.out.print("\nCustomized DB >");
			String query=sobj.nextLine();
			String tokens[]=query.split("\\s");
			
			if(tokens.length==1)
			{
				if(tokens[0].equalsIgnoreCase("exit"))
				{
					System.out.println("exiting from customized db.....");
					break;
				}
				else if(tokens[0].equalsIgnoreCase("cls"))
				{
					if (System.getProperty("os.name").contains("Windows"))
						new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
					else
						Runtime.getRuntime().exec("clear");
					continue;
				}
				else
				{
					System.out.println("Invalid query");
					continue;
				}
			}
			else if(tokens.length==3)
			{
				if((tokens[0].equalsIgnoreCase("create"))&&(tokens[1].equalsIgnoreCase("table"))&&(tokens[2].equalsIgnoreCase("student")))
				{
					obj=new Student();
					obj.insert(1,"ashiwn",32);
					obj.insert(2,"ashish",23);
					obj.insert(3,"nikhil",25);
					System.out.println("Table successfully created...");
				}
				else if((tokens[0].equalsIgnoreCase("delete"))&&(tokens[1].equalsIgnoreCase("from"))&&(tokens[2].equalsIgnoreCase("student")))
				{
					if(obj==null)
					{
						System.out.println("table does not exist");
						continue;
					}
					obj.deleteall();
					System.gc();
					System.out.println("Records deleted successfully");
				}
				else if((tokens[0].equalsIgnoreCase("update"))&&(tokens[1].equalsIgnoreCase("table"))&&(tokens[2].equalsIgnoreCase("student")))
				{
					if(obj==null)
					{
						System.out.println("table does not exist");
						continue;
					}
					obj.update();
				}
				else
				{
					System.out.println("Invalid query");
				}
			}
			else if(tokens.length==4)
			{
				if((tokens[0].equalsIgnoreCase("select"))&&(tokens[1].equalsIgnoreCase("*"))&&(tokens[2].equalsIgnoreCase("from")))
				{
					if(obj==null)
					{
						System.out.println("table does not exist");
						continue;
					}
					if(!tokens[3].equalsIgnoreCase("student"))
					{
						System.out.println("Invalid table name");
						continue;
					}
					else
					{
						obj.display(tokens[1]);
						continue;
					}
					
				}
				else if((tokens[0].equalsIgnoreCase("select"))&&(tokens[1].equalsIgnoreCase("count(*)"))&&(tokens[2].equalsIgnoreCase("from")))
				{
					if(obj==null)
					{
						System.out.println("table student does not exist");
						continue;
					}
					if(!tokens[3].equalsIgnoreCase("student"))
					{
						System.out.println("Invalid table name");
						continue;
					}
					else
					{
						System.out.println(obj.count());
						continue;
					}
				}
				
				else
				{
					System.out.println("Invalid query");
					continue;
				}
			}
			else if(tokens.length==7)
			{
				if((tokens[0].equalsIgnoreCase("insert"))&&(tokens[1].equalsIgnoreCase("into"))&&(tokens[2].equalsIgnoreCase("student"))&&(tokens[3].equalsIgnoreCase("values")))
				{
					if(obj==null)
					{
						System.out.println("Table does not exist");
						continue;
					}
					try
					{
						obj.insert(Integer.parseInt(tokens[4]),tokens[5],Integer.parseInt(tokens[6]));
					}
					catch(Exception e)
					{
						System.out.println("Invalid value for for integer type field");
						continue;
					}
									
				}
				else if((tokens[0].equalsIgnoreCase("delete"))&&(tokens[1].equalsIgnoreCase("from"))&&(tokens[2].equalsIgnoreCase("student"))&&(tokens[3].equalsIgnoreCase("where"))&&(tokens[5].equalsIgnoreCase("=")))
				{
					if(obj==null)
					{
						System.out.println("table does not exist");
						continue;
					}
					if((tokens[4].equals("rno"))||(tokens[4].equals("name")))
					{
						obj.delete(tokens[4],tokens[6]);
						continue;
					}
					else
					{
						System.out.println("Invalid column names");
						continue;
					}
				}
			}
			else
			{
				System.out.println("Invalid query");
				continue;
			}
		}
	}
}