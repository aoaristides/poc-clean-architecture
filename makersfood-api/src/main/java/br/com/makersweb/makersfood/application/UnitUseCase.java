package br.com.makersweb.makersfood.application;

/**
 * @author aaristides
 * @param <IN>
 */
public abstract class UnitUseCase<IN> {

    public abstract void execute(IN aCommand);

}
