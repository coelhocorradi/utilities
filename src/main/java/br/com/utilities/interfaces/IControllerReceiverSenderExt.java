package br.com.utilities.interfaces;

public interface IControllerReceiverSenderExt<DTO, O>
		extends IControllerReceiverSender<DTO>, ISqlResultToList<DTO, O>, ISqlResultToItem<DTO, O> {

}
