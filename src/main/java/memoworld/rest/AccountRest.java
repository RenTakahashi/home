
package memoworld.rest;

import memoworld.entities.Account;
import memoworld.entities.PasswordUtil;
import memoworld.entities.ErrorMessage;
import memoworld.model.AccountModel;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response postAccount(
			Account account
			) {
		String password = account.getPassword();
		String name = account.getName();
		String user_id = account.getUser_id();
		String saftyPassword = PasswordUtil.getSafetyPassword(password, user_id);
		
		if(password == null || password.trim().equals(""))
			return errorMessage(400, "パスワードが入力されていません。パスワードを入力してください");
		if(password.length() < 5) 
			return errorMessage(400, "パスワードが短すぎです。5文字以上のパスワードを入力してください");
		if(password.equals(password.toLowerCase())|| password.equals(password.toUpperCase()))
			return errorMessage(400, "パスワードが小文字または大文字のみになっています。大文字と小文字の両方を含めてください");
		if(name == null || name.trim().equals(""))
			return errorMessage(400, "名前が入力されていません。名前を入力してください");
		if(user_id == null || user_id.trim().equals(""))
			return errorMessage(400, "ログインIDが入力されていません。ログインIDを入力してください");
		try (AccountModel model = new AccountModel()) {
			Account d = model.findByUser_Id(user_id);
			if(d == null) {
			 account = model.register(new Account(saftyPassword,name,user_id));
				return Response.status(201).entity(account).build();
			}
			return errorMessage(400, "すでにログインIDが存在します。異なるログインIDを入力してください");
			}
		}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response getAccounts() {
		try (AccountModel model = new AccountModel()) {
			return Response.ok(model.getAccounts()).build();
		}
	}
	//DB_IDを指定したアカウントを取得する
	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Path("{db_id}")
    public Response getAccount(
            @PathParam("db_id") String idString) {
        try (AccountModel model = new AccountModel()) {
        	int db_id = toInteger(idString);
            if (db_id <= 0) {
                return errorMessage(400, "Bad request");
            }
            Account account = model.findByDb_Id(db_id);
            if (account == null) {
            	return errorMessage(404, "Not found");
            }
            return Response.status(200).entity(account).build();
        }
    }
	//DB_IDを指定してアカウントを削除する
	@DELETE
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@Path("{db_id}")
	public Response deleteAccount(
			@PathParam("db_id") String idString){		
		try (AccountModel model = new AccountModel()){
			int db_id = toInteger(idString);
			if(db_id <= 0) {
				return errorMessage(400, "Bad request");
			}
			if (!model.deleteAccounts(db_id)) {
				return errorMessage(400, "Bad request");
			}
			return Response.status(200).build();
		}
	}

	public Response errorMessage(int statusCode, String message) {
		return Response.status(statusCode).entity(new ErrorMessage(message)).build();
	}
	
	private int toInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
