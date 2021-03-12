package com.laioffer.Controller;

import com.laioffer.entity.Item;
import com.laioffer.external.GitHubClient;
import com.laioffer.service.HistoryService;
import com.laioffer.service.ItemService;
import com.laioffer.service.recommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class recommendationController {

    @Autowired
    private recommendationService recommendService;


    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> recommend(@RequestParam("user_id") String userId,@RequestParam("lat") double lat, @RequestParam("lon") double lon) {

        List<Item> items = recommendService.recommendItems(userId, lat, lon);

        return items;
    }

}
