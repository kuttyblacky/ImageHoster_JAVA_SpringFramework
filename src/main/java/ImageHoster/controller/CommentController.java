package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
	
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ImageService imageService;
	
	//@Autowired
	//private UserService userService;
	

	
	@RequestMapping(value = "image/{imageId}/{imageTitle}/comments",method = RequestMethod.POST)
	public String createComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment")String text,Comment newComment, HttpSession session ){
		
		User user = (User) session.getAttribute("loggeduser");
		
		newComment.setUser(user);
		newComment.setDate(new Date());
		
		Image image =imageService.getImage(imageId);
		newComment.setImages(image);
		newComment.setText(text);
		commentService.createComment(newComment);
		return "redirect:/images/{imageId}/{imageTitle}";
	}

		/*@RequestMapping(value = "/deleteComment",method = RequestMethod.DELETE)
	public String deleteComment(@RequestParam(name="imageId")Integer imageId,HttpSession session)
		{
			Image image=imageService.getImage((imageId));
			//User user= UserService.getUser(userID);

			//model.addAttribute("image", image);
			//User user = (User) session.getAttribute("loggeduser");
			List<Comment> comments = commentService.getCommentsByImageId(imageId);
			commentService.deleteCommentsByImageId(imageId);
			return "images/image";

			*//*if(image.getUser().getId()==user.getId()) {
				commentService.deleteCommentsByImageId(imageId);
				return "redirect:/images";
			}else {
				return  "images/image";
			}*//*


		}*/

}
