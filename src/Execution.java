import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.ib.client.Contract;
import com.ib.client.TickType;
import com.ib.client.examples.util.RequestIDManager;

import classes.Parameters;
import classes.TradingBook;

public class Execution extends ExampleBase{
	
	private int numStocks = 5;
	private String parameterFile = "ParameterText.txt";
	private double lastPrice;
	
    public static void main(String[] args) {
        System.out.println("Execution code");
        new Execution().start();
    }

    public void run() {
        try {
               connectToTWS();
               //Contract contract = createContract("USD", "CASH", "IDEALPRO", "JPY", "201505");
               Contract contract = createContract("IBM", "STK", "NYSE", "USD","201505");
       		   Parameters parameters = new Parameters(numStocks);
       		   readParameters(parameters);
       		   
               Contract[] contracts = new Contract[numStocks];
       		   for (int i = 0; i < numStocks; i++) {
       			   contracts[i] = createContract(parameters.symbols[i], "STK", "NYSE", "USD","201505");
       		   }
       		   
               realTimeTrading(contract, parameters, contracts);
        } catch (Throwable t) {
            System.out.println("Execution.run() :: Problem occurred during processing: " + t.getMessage());
        } finally {
            disconnectFromTWS();
        }
    }

	private void readParameters(Parameters parameters) {
		try {
			File file = new File(parameterFile);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//StringBuffer stringBuffer = new StringBuffer();
			String line;
			int k = 0;
			while ((line = bufferedReader.readLine()) != null) {
				parameters.symbols[k] = line;
		        System.out.print("symbol = ");
		        System.out.println(parameters.symbols[k]);
				k++;
			}
			fileReader.close();
			System.out.println("Contents of file (parameters):");
			for (int i = 0; i < numStocks; i = i+1) {
				System.out.print("symbol = ");
				System.out.println(parameters.symbols[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}

	public void realTimeTrading(Contract contract, Parameters parameters, Contract[] contracts) throws InterruptedException {
        boolean isSuccess = false;
        int waitCount = 0;
        
		//while (true) {
			
			//calc current time; if hour = 00 do the following
			
	        //eClientSocket.reqMktData(RequestIDManager.singleton().getNextRequestId(), contract, null, true);
			//eClientSocket.reqMktData(1, contract, "", false);
			//for (int i = 0; i < numStocks; i++) {
				//eClientSocket.reqMktData(RequestIDManager.singleton().getNextRequestId(), contracts[i], "", false);
					
				while (!isSuccess && waitCount < MAX_WAIT_COUNT) {
					// Check if last price loaded
					if (lastPrice != 0.0) {
						isSuccess = true;
					}
					if (!isSuccess) {
						sleep(WAIT_TIME); // Pause for 1 second
						waitCount++;
					} 
				}
				/*
				if(lastPrice>0){
					System.out.println("Last Price of main loop " + lastPrice);
					//if (!isSuccess) throw new InterruptedException(); //This shouldn't be happening
				}*/
			//}
			//sleep(1000);
			//trade
			//output textfile
		//}
		
		
	}



    public void tickPrice(int tickerId, int field, double price, int canAutoExecute) {
        if (field == TickType.LAST) {
            lastPrice = price;
            System.out.println("Last price" + price);

        }
        System.out.println("price" + price);
    }



}
