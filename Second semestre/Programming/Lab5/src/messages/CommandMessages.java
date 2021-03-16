package messages;

/**
 * Интерфейс класса с описаниями команд
 */
public interface CommandMessages {

    String getAddDescription();

    String getAddIfMaxDescription();

    String getAddIfMinDescription();

    String getClearDescription();

    String getExecuteScriptDescription();

    String getExitDescription();

    String getFilterContainsNameDescription();

    String getHelpDescription();

    String getHistoryDescription();

    String getInfoDescription();

    String getMaxByEyeColorDescription();

    String getPrintFieldDescendingEyeColorDescription();

    String getRemoveByIdDescription();

    String getSaveDescription();

    String getShowDescription();

    String getUpdateDescription();
}
