package br.es.gov.cb.cbmesaudit.services.imps;

import br.es.gov.cb.cbmesaudit.dtos.AuditFilterDTO;
import br.es.gov.cb.cbmesaudit.dtos.AuditRequestDTO;
import br.es.gov.cb.cbmesaudit.dtos.AuditResponseDTO;
import br.es.gov.cb.cbmesaudit.entities.AuditEntity;
import br.es.gov.cb.cbmesaudit.mapper.AuditMapper;
import br.es.gov.cb.cbmesaudit.repositorys.AuditRepository;
import br.es.gov.cb.cbmesaudit.services.AuditService;
import br.es.gov.cb.cbmesaudit.specification.AuditSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {
	
	private final AuditRepository auditRepository;

	@Async
	@Override
	public void create(AuditRequestDTO auditRequestDTO) {
		auditRepository.save(AuditMapper.createAuditEntityFromDTO(auditRequestDTO));
	}
	
	@Override
	public Page<AuditResponseDTO> findAll(Pageable pageable, AuditFilterDTO auditFilterDTO) {
		Specification<AuditEntity> spec = AuditSpecification.getByFilter(auditFilterDTO);
		Page<AuditEntity> page = auditRepository.findAll(spec, pageable);
		
		List<AuditResponseDTO> audits = page.getContent()
				.stream()
				.map(AuditMapper::createAuditDTOFromEntity)
				.toList();

		return new PageImpl<>(audits);
	}
}
