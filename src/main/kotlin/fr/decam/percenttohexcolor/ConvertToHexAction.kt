package fr.decam.percenttohexcolor

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project

object ConvertToHexAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        // Get all the required data from data keys
        // Editor and Project were verified in update(), so they are not null.

        // Get all the required data from data keys
        // Editor and Project were verified in update(), so they are not null.
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = e.getRequiredData(CommonDataKeys.PROJECT)
        val document: Document = editor.document
        // Work off of the primary caret to get the selection info
        // Work off of the primary caret to get the selection info
        val primaryCaret: Caret = editor.caretModel.primaryCaret
        val start: Int = primaryCaret.getSelectionStart()
        val end: Int = primaryCaret.getSelectionEnd()
        // Replace the selection with a fixed string.
        // Must do this document change in a write action context.
        // Replace the selection with a fixed string.
        // Must do this document change in a write action context.
        WriteCommandAction.runWriteCommandAction(project
        ) { document.replaceString(start, end, "editor_basics") }
        // De-select the text range that was just replaced
        // De-select the text range that was just replaced
        primaryCaret.removeSelection()
    }

    override fun update(e: AnActionEvent) {
        // Get required data keys
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        // Set visibility and enable only in case of existing project and editor and if a selection exists
        e.presentation.isEnabledAndVisible = project != null && editor != null && editor.selectionModel.hasSelection()
    }
}
