package jp.bcat;import java.io.*;import java.util.*;public class Main {	protected Map<String, CLICommand> commandMap = new HashMap<String, CLICommand>();	protected String command_list = "";	private void makeMap(String key_num, String com_j, String key_name, CLICommand command) {		this.commandMap.put(key_num, command);		this.commandMap.put(key_name, command);		this.command_list += "[" + key_num + "] " + com_j + "(" + key_name + ") ";	}		public Main() {		BookCatalog catalog = BookCatalog.getInstance();		this.makeMap("1", "一覧", "list", new ListCommand(catalog));		this.makeMap("2", "登録", "add", new AddCommand(catalog));		this.makeMap("3", "削除", "delete", new DeleteCommand(catalog));		this.makeMap("4", "検索", "search", new SearchCommand(catalog));		this.makeMap("0", "終了", "exit", new ExitCommand());	}	public void process(BufferedReader in, PrintWriter out) throws IOException {		while (true) {			out.println(this.command_list);			out.print(": ");			out.flush();			String line = in.readLine();			CLICommand command				= (CLICommand)commandMap.get(line);			if (command != null)				command.process(in, out);			else				out.println("コマンドを正しく入力してください。");		}	}	public static void main(String args[]) {		try {			BufferedReader in = new BufferedReader(					new InputStreamReader(System.in));			PrintWriter out = new PrintWriter(					new OutputStreamWriter(System.out), true);			Main main = new Main();			main.process(in, out);		} catch (Exception e) {			e.printStackTrace();		}	}}