package com.example.springkadaiform.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;


@Controller
public class ContactFormController {
	
	//フォーム画面のビューを表示
	@GetMapping("/form")
	public String form(Model model) {
		// すでにインスタンスが存在する場合は行わない
        if (!model.containsAttribute("contactForm")) {
            // ビューにフォームクラスのインスタンスを渡す
            model.addAttribute("contactForm", new ContactForm());
        }
        
		return "contactFormView";
	}
	
	//確認画面のビューを表示
	@GetMapping("/confirm")
	public String confirm(@ModelAttribute("contactForm") ContactForm contactForm,Model model) {
        // すでにインスタンスが存在する場合は行わない
        if (!model.containsAttribute("contactForm")) {
            // checkform時に保存したリダイレクトのデータをビューにフォームクラスのインスタンスを渡す
            model.addAttribute("contactForm", new ContactForm());
        }
        return "confirmView";
	}
	
	
	//確認画面
	@PostMapping("/checkform")
	public String checkform(RedirectAttributes redirectAttributes,
        @Validated ContactForm form, BindingResult result) {
		//System.out.println(form.getName()+"aaaa"+  form.getEmail() +"aaaa"+ form.getMassage());
		// バリデーションエラーがあったら終了
        if (result.hasErrors()) {
            // フォームクラスをビューに受け渡す
            redirectAttributes.addFlashAttribute("contactForm", form);
            // バリデーション結果をビューに受け渡す
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
                    + Conventions.getVariableName(form), result);

            // エラーがある場合はformへリダイレクトしてエラーを再表示
            return "redirect:/form";
        }
        try {
        	redirectAttributes.addFlashAttribute("contactForm", form);
        } catch(Exception e) {
        	//特に何もしない
        }
        // confirmにリダイレクトして問い合わせ内容を再表示
        return "redirect:/confirm";       
	}
}
