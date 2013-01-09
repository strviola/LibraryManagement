package jp.bcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditCommand implements CLICommand {
	private BookCatalog catalog;
	
	public EditCommand(BookCatalog catalog) {
		this.catalog = catalog;
	}

	@Override
	public void process(BufferedReader in, PrintWriter out) throws IOException {
		out.print("編集する図書ID: ");
		out.flush();
		Book bookToEdit = catalog.getBook(in.readLine());
		if (bookToEdit == null) {
			out.println("該当する図書がありません。");
			return;
		}
		new BookWriter(out, "- ").write(bookToEdit);
		out.println("どの情報を編集するか選択してください。");
		out.println("[1]" + "タイトル");
		out.println("[2]" + "著者");
		out.println("[3]" + "訳者");
		out.println("[4]" + "出版社");
		out.println("[5]" + "出版年月日");
		out.println("[6]" + "ISBN/ISSN");
		out.println("[7]" + "備考");
		out.println("[8]" + "キーワード");
		out.println("[9]" + "登録者");
		out.println("[0]" + "(操作の中断)");
		// information to edit
		int type;
		while (true) {
			out.print(": ");
			out.flush();
			try {
				type = Integer.parseInt(in.readLine());
				if (type >= 1 && type <= 9) break;
				else if (type == 0) {
					out.println("キャンセルされました。");
					return;
				} else {
					out.println("正しい情報を入力してください。");
				}
			} catch (NumberFormatException e) {
				out.println("正しい情報を入力してください。");
			}
		}
		out.println("どのように編集するか入力してください: ");
		out.println("(「exit」で中断します)");
		String value = in.readLine();
		if (value.equals("exit")) {
			out.println("キャンセルされました。");
			return;
		}
		switch (type) {
		case 1:
			bookToEdit.setTitle(value);
			break;
		case 2:
			bookToEdit.setAuthor(value);
			break;
		case 3:
			bookToEdit.setTranslator(value);
			break;
		case 4:
			bookToEdit.setPublisher(value);
			break;
		case 5:
			bookToEdit.setPublicationDate(value);
			break;
		case 6:
			bookToEdit.setCode(value);
			break;
		case 7:
			bookToEdit.setMemo(value);
			break;
		case 8:
			bookToEdit.setKeyword(value);
			break;
		case 9:
			bookToEdit.setDataCreator(value);
			break;
		}
		String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		bookToEdit.setDataCreatedDate(now);
		new BookWriter(out, "+ ").write(bookToEdit);
		out.println("この内容で登録されました。");
	}
}
