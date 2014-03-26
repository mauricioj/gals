package br.ufscar.sigam.interfaces;

public interface TaskInterface<T> {
	public abstract void executeAfterAsyncTask(T result, Exception exception, int id);
}
