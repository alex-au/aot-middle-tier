package com.aot.aotmiddletier.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource; 

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class OptionSearchService {
	
  public class SearchParam{
	  private int providerId;
	  
	  public SearchParam(int providerId){
		  this.providerId = providerId;
	  }
	  public int getProviderId(){
		  return providerId;
	  }
	  public String toString(){
		  return "Search Parameter: providerId<" + providerId + ">";
	  }
  }

  @SuppressWarnings("rawtypes")
  private class OptionSerachTask implements Callable{

    private SearchParam param;
    
    public OptionSerachTask(SearchParam param) {
      this.param = param; 
    }
    public Object call() {
      return param.toString();
    }
  }

  @Resource
  private ThreadPoolTaskExecutor taskExecutor;


  public void doSearch(){
	  List<Future<String>> results = sendRequest();
	  mergeResult(results);
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private List<Future<String>> sendRequest() {
	  Callable callable = null;
	  Collection collection = new ArrayList();
	  
	  // Divide into sub requests, here temporarily hardcoded to 25 subtasks
      for(int i = 0; i < 25; i++) {
    	  callable = new OptionSerachTask(new OptionSearchService.SearchParam(i));
    	  collection.add(callable);
      }
      
      //test
      List<Future<String>> results = null;
      try{
    	  results = (List<Future<String>>)taskExecutor.getThreadPoolExecutor().invokeAll(collection, 10, TimeUnit.SECONDS);
      }catch(InterruptedException e){
    	  e.printStackTrace();
      }
      
      return results;
  }
  
  private void mergeResult(List<Future<String>> results){
	  for( Future<String> row : results) {
    	  try {
			System.out.println(row.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
  }
}



