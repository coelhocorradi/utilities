package br.com.utilities.interfaces;

public interface IControllerSenderExt<DTO, O>
		extends IControllerSender<DTO>, ISqlResultToList<DTO, O>, ISqlResultToItem<DTO, O> {

}
