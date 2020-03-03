package loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import products.Coffee;
import products.CoffeeBrewer;
import products.Product;
import sale.Catalog;

public class FileCatalogLoader implements CatalogLoader {
	
	private static final int PRO_NUM = 3; // product number
	private static final int COF_NUM = 9; // coffee number
	private static final int BRE_NUM = 6; // browser number
	private StringTokenizer stringTokenizer = null;
	private String[] strings = null;
	private int i = 0;
	
	private Product readProduct(String line)
			throws DataFormatException {
		Product product = null;
		i = 0;
		
		try {
			strings = new String[PRO_NUM];
			stringTokenizer = new StringTokenizer(line, "_");
			stringTokenizer.nextToken();
			
			while ((stringTokenizer.hasMoreTokens()) && (i < PRO_NUM)) {
				strings[i] = stringTokenizer.nextToken();
				i++;
			}
			
			// whether the value of array is null or not
			for (int j = 0; j < PRO_NUM; ++j) {
				if(strings[j] == null) {
					throw new DataFormatException(line);
				}
			}
			// whether the number of tokens is wrong
			if (stringTokenizer.hasMoreTokens()) {
				throw new DataFormatException(line);
			}
			
			product = new Product(strings[0], strings[1], new Double(strings[2]));
			
			// StringTokenizer's constructor may throws this exception 
		} catch (NullPointerException e) {
			e.printStackTrace();
			// when changing the String to a double value may throws this Exception
		} catch (NumberFormatException e) {
			throw new DataFormatException(line); 
			// array may throws this Exception
		} catch (IndexOutOfBoundsException e) {
			  e.printStackTrace();
		}
			return product;
	}

	private Coffee readCoffee(String line)
		   throws DataFormatException {
			   Coffee coffee = null;
			   i = 0;
			   
			   try {
				   stringTokenizer = new StringTokenizer(line, "_");
				   strings = new String[COF_NUM];
				   stringTokenizer.nextToken();
				   while (stringTokenizer.hasMoreTokens() && i < COF_NUM) {
					   strings[i] = stringTokenizer.nextToken();
					   i++;
				   }
				   
				   for (int j = 0; j < COF_NUM; ++j) {
					   if (strings[j] == null) {
						   throw new DataFormatException(line);
					   }
				   }
				   if (stringTokenizer.hasMoreTokens()) {
					   throw new DataFormatException(line);
				   }
				   
				   coffee = new Coffee(strings[0], strings[1], new Double(strings[2]), strings[3], 
						   strings[4], strings[5], strings[6], strings[7], strings[8]);
			   } catch (NullPointerException e) {
					e.printStackTrace();
					
			   } catch (NumberFormatException e) {
					throw new DataFormatException(line); 
			   } catch (IndexOutOfBoundsException e) {
					  e.printStackTrace();
			   }
					return coffee;
	}
	
	private CoffeeBrewer readCoffeeBrewer(String line) 
		   throws DataFormatException {
		CoffeeBrewer coffeeBrewer = null;
		int i = 0;
		
		try {
			  stringTokenizer = new StringTokenizer(line, "_");
			  strings = new String[BRE_NUM];
			  stringTokenizer.nextToken();
			  while (stringTokenizer.hasMoreTokens() && i < BRE_NUM) {
				  strings[i] = stringTokenizer.nextToken();
				  i++;
			  }
			   
			  for (int j = 0; j < BRE_NUM; ++j) {
				  if (strings[j] == null) {
					  throw new DataFormatException(line);
				  }
			  }
			  if (stringTokenizer.hasMoreTokens()) {
				  throw new DataFormatException(line);
			  }
			   
			   coffeeBrewer = new CoffeeBrewer(strings[0], strings[1], new Double(strings[2]), strings[3], strings[4], new Integer(strings[5]));
		  } catch (NullPointerException e) {
			e.printStackTrace();
				
		  } catch (NumberFormatException e) {
			throw new DataFormatException(line); 
		  } catch (IndexOutOfBoundsException e) {
			  e.printStackTrace();
		  }
		
		 return coffeeBrewer;
	}


	public Catalog loadCatalog(String filename)
			throws FileNotFoundException, 
		           IOException,
		           DataFormatException	{
		Catalog catalog = new Catalog();
		String str;
		
		try {
			 @SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(filename));
			
			while ((str = in.readLine()) != null) {
				if(str.startsWith("Product")) {
					catalog.addItems(readProduct(str));
				} else if(str.startsWith("Coffee")) {
					catalog.addItems(readCoffee(str));
				} else if (str.startsWith("Brewer")) {
					catalog.addItems(readCoffeeBrewer(str));
				} else {
					throw new DataFormatException();
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("the specified file does not exist");
		} catch (IOException e) {
			System.out.println("there is an error reading the information in the specified file. ");
		}
		
		return catalog;
	}

}
