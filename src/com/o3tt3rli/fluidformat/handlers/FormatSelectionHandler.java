package com.o3tt3rli.fluidformat.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.ITextEditor;

import com.o3tt3rli.fluidformat.FluidFormat;

/**
 * @author krizz
 */
public class FormatSelectionHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		IEditorPart editor = window.getActivePage().getActiveEditor();
		if (editor instanceof ITextEditor) {
			formatSel((ITextEditor) editor);
		}
		return null;
	}

	private void formatSel(ITextEditor editor) {
		try {
			ITextSelection sel = (ITextSelection) editor.getSelectionProvider().getSelection();
			IDocument document = editor.getDocumentProvider().getDocument(editor.getEditorInput());
			int offset = sel.getOffset();
			int length = sel.getLength();
			if (length == 0) {
				int[] expanded = expand(document, offset);
				offset = expanded[0];
				length = expanded[1];
			}

			if (length == 0) {
				return;
			}

			int lineOffset = document.getLineOffset(document.getLineOfOffset(offset));

			String spaces = leadingWhitespaces(document.get(lineOffset, offset - lineOffset));
			String approxSpaces = spaces + spaces;
			document.replace(offset, length, FluidFormat.format(document.get(offset, length), approxSpaces));
		} catch (Exception e) {
			// ignore
		}
	}

	/**
	 * @return [offset, length]
	 */
	private int[] expand(IDocument document, int offset) throws Exception {

		int start = offset;
		int firstDot = start;
		while (start >= 0) {
			char c = document.getChar(start);
			if (c == '.') {
				firstDot = start;
			} else if (c == '{' || c == '}' || c == ';') {
				break;
			}
			start--;
		}

		int end = offset;
		int len = document.getLength();
		while (end < len) {
			char c = document.getChar(end);
			if (c == '{' || c == '}' || c == ';') {
				break;
			}
			end++;
		}
		return new int[] { firstDot, end - firstDot };
	}

	private static String leadingWhitespaces(String s) {
		String ret = "";
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				ret += c;
			} else {
				break;
			}
		}
		return ret;
	}
}
