//package jp.co.sss.crud.filter;
//
////11章 ソースコード11-7,8 作成
///** 注意 **
//  他のサンプルプログラムの動作に影響を及ぼすため、「@Component」をコメントアウトしています。
//  動作確認をする際は、@Componetを有効にしてください。
// 
// **/
//
//import java.io.IOException;
//
//import org.springframework.stereotype.Component;
//
////import org.springframework.stereotype.Component; 
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
////TODO ※@Componentの部分のコメントを外すと動作します。
////TODO ※他のサンプルコードの動作に影響するため、コメントアウトしています。
//@Component   
//public class LoginCheckFilter extends HttpFilter {
//	
//    @Override
//    public void doFilter(
//      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//      throws IOException, ServletException {
//        // リクエストURLを取得
//        String requestURL = request.getRequestURI();
//        System.out.println(requestURL);
//        if (requestURL.endsWith("/loginWithValidation")) {
//            //リクエスト URL が「ログイン画面への遷移処理」、 
//            //「ログイン処理」宛ての場合、ログインチェックを実施せず、 
//            //リクエスト対象のコントローラの処理に移る 
//        	System.out.println("①url〇"+request+response);
//            chain.doFilter(request, response);
//        } else {
//            //セッション情報を取得 
//            HttpSession session = request.getSession();
//            //セッション情報からユーザのログイン情報(セッション属性 userId)を取得
//			System.out.println("②url×" +session.getAttribute("manage"));
//            Integer manage = (Integer) session.getAttribute("manage");
//
//            if (manage == null) {
//                //ログイン情報が存在しない場合（ログイン情報(userId) が null の場合）、 
//                //ログイン画面にリダイレクトする 
//            	System.out.println("③中身なし:"+response);
//                response.sendRedirect("/spring_crud/loginWithValidation");
//                return ;
//            }else {
//            	System.out.println("④中身あり"+request+response);
//                chain.doFilter(request, response);
//            }
//        }
//    }
//}
//
