package com.societe.calculetteservicehistomenu.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.societe.calculetteservicehistomenu.exceptions.DivisionParZeroException;

public class CalculetteService extends Service {

	private final IBinder liaison = new ServiceLiaison();

	public class ServiceLiaison extends Binder{
		public CalculetteService getService(){
			return CalculetteService.this;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return liaison;
	}


	public double additionner(final double op1, final double op2) {
		try {
			Thread.sleep(10000);	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return op1+op2;
	}

	public double soustraire(final double op1, final double op2){
		return  op1 - op2;
	}

	public double multiplier(final double op1, final double op2){
		return  op1 * op2;
	}

	public double diviser(final double op1, final double op2) throws DivisionParZeroException{
		if (op2 == 0){
			throw new DivisionParZeroException();
		}
		return op1/ op2;
	}
}









