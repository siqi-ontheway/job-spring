package com.laioffer.Controller;


import com.laioffer.entity.Item;
import com.laioffer.external.GitHubClient;
import com.laioffer.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class itemController {

    @Autowired
    private HistoryService historyService;


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> search(@RequestParam("user_id") String userId,@RequestParam("lat") double lat, @RequestParam("lon") double lon) {

        Set<String> favoritedItemIds = historyService.getFavoriteItemIds(userId);
        GitHubClient client = new GitHubClient();
        List<Item> items = client.search(lat,lon,null);

        for (Item item : items) {
            item.setFavorite(favoritedItemIds.contains(item.getId()));
        }
        return items;
    }

}

