package com.example.sportrental.listing

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/listings")
class ListingViewController(
    private val listingService: ListingService
) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("listings", listingService.findAll())
        return "listings/list"
    }

    @GetMapping("/new")
    fun createForm(model: Model): String {
        model.addAttribute("listingForm", ListingForm())
        model.addAttribute("statuses", ListingStatus.entries)
        model.addAttribute("pageTitle", "Создание объявления")
        return "listings/form"
    }

    @PostMapping
    fun create(
        @Valid @ModelAttribute("listingForm") form: ListingForm,
        bindingResult: BindingResult,
        model: Model,
        redirectAttributes: RedirectAttributes
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ListingStatus.entries)
            model.addAttribute("pageTitle", "Создание объявления")
            return "listings/form"
        }

        listingService.create(listingService.toRequest(form))
        redirectAttributes.addFlashAttribute("successMessage", "Объявление успешно создано")
        return "redirect:/listings"
    }

    @GetMapping("/{id}/edit")
    fun editForm(@PathVariable id: Long, model: Model): String {
        val listing = listingService.findById(id)
        model.addAttribute("listingForm", listingService.toForm(listing))
        model.addAttribute("statuses", ListingStatus.entries)
        model.addAttribute("pageTitle", "Редактирование объявления")
        return "listings/form"
    }

    @PostMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @ModelAttribute("listingForm") form: ListingForm,
        bindingResult: BindingResult,
        model: Model,
        redirectAttributes: RedirectAttributes
    ): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ListingStatus.entries)
            model.addAttribute("pageTitle", "Редактирование объявления")
            return "listings/form"
        }

        listingService.update(id, listingService.toRequest(form))
        redirectAttributes.addFlashAttribute("successMessage", "Объявление успешно обновлено")
        return "redirect:/listings"
    }

    @PostMapping("/{id}/delete")
    fun delete(@PathVariable id: Long, redirectAttributes: RedirectAttributes): String {
        listingService.delete(id)
        redirectAttributes.addFlashAttribute("successMessage", "Объявление успешно удалено")
        return "redirect:/listings"
    }
}
