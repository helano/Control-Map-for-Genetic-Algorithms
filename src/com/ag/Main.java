package com.ag;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class Main {


	   static int limSup = 10;
	   static int limInf = -10;



	   static int tamanhoPop = 0;
	   static int precisao =1;
	   static int geracoes = 100;
	   static int percentualDeMultacao = 5; //%
	   static int tamanhoCromossomo;
	   static int tamtorneio = 0;
	   static int crossover_rate=0;
	   static int testeEficiencia = 30;
	   static int contadorAcerto=0;
	   static  Population populacao;
	   static int otimo = 0;


	   static int pop_inicial=200;
	   static int pop_final=3000;
	   static int pop_step=200;
	   static int crossover_inicial=10;
	   static int crossover_final=100;
	   static int crossover_step=10;
	   static int torneio_inicial=2;
	   static int torneio_final=16;
	   static int torneio_step=2;

	   static ArrayList<Integer> pop_conf = new ArrayList<Integer>();
	   static ArrayList<Integer> torn_conf = new ArrayList<Integer>();
	   static ArrayList<Integer> cross_conf = new ArrayList<Integer>();



	public static void main(String[] args) throws IOException {
		int[] IntegerList;

		double fitnessMedio = 0;

for (tamanhoPop = pop_inicial;tamanhoPop<=pop_final; tamanhoPop+=pop_step) {
	for (crossover_rate = crossover_inicial;crossover_rate<=crossover_final; crossover_rate+=crossover_step) {
	for (tamtorneio = torneio_inicial; tamtorneio<=torneio_final; tamtorneio+=torneio_step) {


				System.out.println("CONFIGURACOES:" +tamanhoPop +":"+crossover_rate+":"+tamtorneio);




				double[] fitnessList = new double [tamanhoPop];


				tamanhoCromossomo= (int)calculaTamanho(limSup, limInf);

				populacao = new Population( tamanhoCromossomo, tamanhoPop);

				populacao.gerarIndividuos(limSup, limInf);

							for (int teste =0; teste<testeEficiencia; teste++) {

									for (int g = 0; g < geracoes; g++) {

										//System.out.println("Geração:" + g);
										//System.out.println("solving integer");

													IntegerList = listaInteiros(populacao);


													//System.out.println("solving double");
													double[] doubleList =  getDouble(IntegerList);


													//System.out.println("solving Fitness");
													fitnessList = fitness(doubleList);

													 fitnessMedio =0;
										        if (g ==0){
										        	for (int i=0;i<tamanhoPop;i++  ){
										        		// System.out.println(i+"=("+fitnessList[i]+")");
										        		fitnessMedio+=fitnessList[i];
										        	}
										        	//System.out.println("FITNESS MEDIO =============("+fitnessMedio/fitnessList.length+")");

										        }

										        	//System.out.println("Selecting ");

										        	int [] selecionados = select(fitnessList);

										        	//System.out.println("doing crossover");
										        	populacao = cruzamento(populacao, fitnessList);

										        	//System.out.println("mutating");
										        	populacao = mutacao(populacao);

										}

									fitnessMedio =0;
							        for (int i=0;i<tamanhoPop;i++  ){
							          // System.out.println(i+"=("+fitnessList[i]+")");
							            fitnessMedio+=fitnessList[i];
							            if (fitnessList[i]<fitnessList[otimo] ){
							               otimo = i;
							            }

							        }
							         //System.out.println("FITNESS MEDIO ª Populacao final =============("+fitnessMedio/tamanhoPop+")");

							      //  System.out.println("Fitness MELHOR INDIVIDUO("+fitnessList[otimo]);

							        //System.out.println("NUMERODE ACERTOS: "+contadorAcerto);




									 for (int i=0;i<tamanhoPop;i++  ){
								          // System.out.println(i+"=("+fitnessList[i]+")");
								            fitnessMedio+=fitnessList[i];
								            if (fitnessList[i]<fitnessList[otimo] ){
								               otimo = i;
								            }

								        }

									 if (fitnessList[otimo] <= 0.09) {
										 contadorAcerto++;

									 }

									}

									if (contadorAcerto>=29) {
										System.out.print("configuracao funcionou");
										pop_conf.add(tamanhoPop);
										torn_conf.add(tamtorneio);
										cross_conf.add(crossover_rate);


									}

									contadorAcerto = 0;
								}
							}
						}




					try {
						String fileName = "E:\\configuracoes_AG.xls";
						WritableWorkbook workbook = Workbook.createWorkbook(new File(fileName));

						WritableSheet sheet = workbook.createSheet("Sheet", 0);


						for (int i =0; i<pop_conf.size(); i++) {





					                  Number number = new Number(0,i, pop_conf.get(i) );
					                  sheet.addCell(number);
					                  number = new Number(1,i,cross_conf.get(i));
					                   sheet.addCell(number);
					                   number = new Number(2,i,torn_conf.get(i) );

					                   sheet.addCell(number);



						}


					workbook.write();
					workbook.close();
					System.out.print("deu certo");

					}catch (WriteException e) {

					}



	}


	  static double calculaTamanho(int limS, int limI){
	        int tamanho;
	        int  tamanhoBit=0;
	        tamanho  = (limS - limI);
	        //calculando potencias de 2 para encontrar quantos bits precisam ser usados para
	        //representar o intervalo
	            for (int i = 0; ((Math.pow(10, precisao)) * tamanho) > Math.pow(2, i) ; ++i ){
	                tamanhoBit = i;
	            }
	            System.out.print("Tamanho dos cromossomos:'"+tamanhoBit+"'");
	        return (tamanhoBit);
	    }

	  static int[] listaInteiros (Population pop){

		     //System.out.println("calculando inteiros:");
		     int [] fitnessList = new int[tamanhoPop];
		     int []listaInteiros = new int[tamanhoPop];

		     for (int i=0; i<tamanhoPop; i++){
		         for (int j = (tamanhoCromossomo-1); j>=0;j--){
		                 if (pop.cromossomos[i][j] ==1){
		                    listaInteiros[i]+=Math.pow(2,j) ;
		                 }
		            }
		         //System.out.println(listaInteiros[i]);
		     }
		    return listaInteiros;
		  }


	  static double[] getDouble ( int[] listaInteiros){
	        double[] legendaReal = new double[tamanhoPop];

	        for (int i =0 ; i<tamanhoPop; i++){
	            legendaReal[i] =  + limInf + listaInteiros[i]*(limSup-limInf)/(Math.pow(2,tamanhoCromossomo)-1);


	            //System.out.println("Correspondente:"+legendaReal[i]);
	        }

	    return legendaReal;
	    }


	  static double[] fitness ( double[] doubleList  ){

		  double[] fitnessList = new double[tamanhoPop];
          for (int i = 0; i< tamanhoPop; i++){
          fitnessList[i] = Math.pow(doubleList[i], 2);
          }


	   return fitnessList;
	   }
	  static int[] select (double [] fitnessList) {


		  int [] selecionados = new int[tamtorneio];
		  int [] Parents = new int[2];

		  		for (int i = 0; i<tamtorneio; i++) {
		  			Random rand = new Random ();
		  			selecionados[i] = rand.nextInt(tamanhoPop);
		  			if (i==0) {
			  				Parents[0] = selecionados[i];
			  				Parents[1] = selecionados[i+1];
		  			}else {

		  			  if (fitnessList[Parents[0]] < fitnessList[selecionados[i]]){
			  				Parents[1] = Parents[0];
			  				Parents[0]= selecionados[i];

                       }

		  			}

		  		}


		  return Parents;
	  }




	  static Population cruzamento ( Population pop, double [] fitnessList ){
          Population novaPopulacao  = new Population(tamanhoCromossomo, tamanhoPop);

          			for ( int i=0; i<tamanhoPop; i++) {
          				int[] Parents =  select ( fitnessList);

          			   Random rand = new Random();
          		        int prob;
          		        prob = rand.nextInt(100);

          		        if (prob <= crossover_rate) {
          				novaPopulacao.cromossomos[i] = reproduce (pop.cromossomos[Parents[0]], pop.cromossomos[Parents[1]]);
          		        }else {
          		        	novaPopulacao.cromossomos[i] =  pop.cromossomos[Parents[0]];
          		        }



          			}



			return novaPopulacao;
			}


	  static public Integer[] reproduce(Integer[] father, Integer[] mother) {
	        Integer[] child=new Integer[father.length];
	        int crossPoint = (father.length/4); //(int) (Math.random()*father.length);//make a crossover point


	            for (int i=0;i<crossPoint;++i){
	                           child[i]=mother[i];

	            }
	            for (int i =crossPoint; i<(crossPoint*2);i++ ){
	                    child[i]=father[i];

	            }
	            for(int i=(crossPoint*2); i<(crossPoint*3); i++){
	                    child[i]=mother[i];

	            }
	            for(int i=(crossPoint*3); i<father.length; i++){
	                    child[i]=father[i];

	            }


	     return child;
	    }

	  static Population mutacao ( Population pop) {
		  int percentualMutacao = (int) (tamanhoPop*percentualDeMultacao)/100;
	       // individuosMutados = new int[percentualMutacao];
	        double auxmut =  Math.round( tamanhoCromossomo*percentualDeMultacao);
	            int auxrandom;


	             for( int i =0;i<tamanhoPop;i++){
	                //  numeros aleatorios apatir do tamanho do cromossomo
	                 auxrandom = (int)Math.floor(Math.random()*tamanhoCromossomo);

	                    for(int j=0;j<auxmut;j++){
	                      if(pop.cromossomos[i][auxrandom]==0)
	                          pop.cromossomos[i][auxrandom]=1;
	                      else
	                          pop.cromossomos[i][auxrandom]=0;



	        }

	     }

//	            for (int i =0; i<percentualMutacao; i++){
//	                System.out.print(i+":");
//	                for (int j =0; j<tamanhoCromossomo; j++){
//	                     System.out.print( pop.cromossomos[individuosMutados[i]][j]);
//	                }
//	              System.out.println();
//	            }

	     return pop;
	     }




	   static  void salva_excel() throws IOException {



	  }



}
