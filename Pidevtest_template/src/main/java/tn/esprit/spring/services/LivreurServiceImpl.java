package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.spring.entities.Livreur;
import tn.esprit.spring.repository.LivreurRepository;


@Service
public class LivreurServiceImpl implements ILivreurService {

	@Autowired
	LivreurRepository livreurRepository;

	private static final Logger L = LogManager.getLogger(ILivreurService.class);

	@Override
	public Livreur save(Livreur liv) {
		return livreurRepository.save(liv);
	}

	@Override
	public List<Livreur> findall() {
		List<Livreur> a = livreurRepository.findAll();

		for (Livreur Livreurs : a) {
			L.info("Livreurs :" + Livreurs);

		}
		return a;

	}

	@Override
	public void delete(long id) {
		livreurRepository.deleteById(id);

	}

	@Override
	public Livreur updateLiv(Livreur Liv) {
		return livreurRepository.save(Liv);
	}

}
